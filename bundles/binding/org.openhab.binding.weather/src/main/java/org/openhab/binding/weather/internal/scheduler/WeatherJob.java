/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.weather.internal.scheduler;

import org.openhab.binding.weather.internal.bus.WeatherPublisher;
import org.openhab.binding.weather.internal.common.LocationConfig;
import org.openhab.binding.weather.internal.common.WeatherContext;
import org.openhab.binding.weather.internal.provider.WeatherProvider;
import org.openhab.binding.weather.internal.provider.WeatherProviderFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Weather job which fetches weather data from a provider and publishes the
 * state to openHab.
 *
 * @author Gerhard Riegler
 * @since 1.6.0
 */
public class WeatherJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(WeatherJob.class);
    protected WeatherContext context = WeatherContext.getInstance();
    protected WeatherPublisher weatherPublisher = WeatherPublisher.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobContext.getJobDetail().getJobDataMap();

        String locationId = jobDataMap.getString("locationId");
        logger.debug("Starting Weather job for location '{}'", locationId);

        try {
            LocationConfig locationConfig = context.getConfig().getLocationConfig(locationId);
            WeatherProvider weatherProvider = WeatherProviderFactory
                    .createWeatherProvider(locationConfig.getProviderName());
            context.setWeather(locationId, weatherProvider.getWeather(locationConfig));
            weatherPublisher.publish(locationId);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new JobExecutionException(ex.getMessage(), ex);
        }
    }

}
