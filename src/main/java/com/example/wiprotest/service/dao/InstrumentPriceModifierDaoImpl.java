package com.example.wiprotest.service.dao;import com.example.wiprotest.exceptions.DaoException;import com.example.wiprotest.model.InstrumentPriceModifier;import com.example.wiprotest.service.InstrumentPriceModifierDao;import javax.sql.DataSource;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;public class InstrumentPriceModifierDaoImpl implements InstrumentPriceModifierDao {	private final DataSource dataSource;	public InstrumentPriceModifierDaoImpl(DataSource dataSource) {		this.dataSource = dataSource;	}	private static PreparedStatement createGetByNameStatement(Connection connection, String name) throws SQLException {		PreparedStatement statement = connection.prepareStatement(			"SELECT ID, NAME, MULTIPLIER FROM INSTRUMENT_PRICE_MODIFIER WHERE NAME = ?"		);		statement.setString(1, name);		return statement;	}	@Override	public InstrumentPriceModifier getByName(String name) throws DaoException {		try (			Connection connection = dataSource.getConnection();			PreparedStatement statement = createGetByNameStatement(connection, name);			ResultSet resultSet = statement.executeQuery()		) {			if (resultSet.next()) {				return new InstrumentPriceModifier(					resultSet.getLong("ID"),					resultSet.getString("NAME"),					resultSet.getDouble("MULTIPLIER")				);			} else {				return null;			}		} catch (SQLException sqlException) {			throw new DaoException(sqlException);		}	}}