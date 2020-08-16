package com.nextep.lambda;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class WeatherRepository {

	// TODO change to your values
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://weather-dev.comr1j6zhptd.us-east-2.rds.amazonaws.com:3306/weather";
	private static String userName = "admin";
	private static String password = "test1234";

	public Weather getWeatherFor(String zipCode) {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);

		return template.queryForObject("select * from weather.temp_look_up where zip = ?",
				new Object[] { zipCode }, new RowMapper<Weather>() {

					@Override
					public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {

						String temp = rs.getString("temp");

						Weather weather = new Weather();
						weather.setTemp(temp);
						weather.setZipCode(zipCode);

						return weather;
					}
				});

	}
}
