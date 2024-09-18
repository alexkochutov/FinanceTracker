package org.financetracker.dao;

import org.financetracker.model.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDAO {

    private final Connection connection;

    private static final String QUERY_INSERT = """
            INSERT INTO public.address (country, city, street, building, stage, apartment, doorcode)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String QUERY_SELECT = """
            SELECT FROM public.address
            WHERE id = ?;
            """;

    private static final String QUERY_SELECT_ALL = """
            SELECT FROM public.address;
            """;

    private static final String QUERY_UPDATE = """
            UPDATE public.address
            SET country = ?,
                city = ?,
                street = ?,
                building = ?,
                stage = ?,
                apartment = ?,
                doorcode = ?
            WHERE id = ?;
            """;

    private static final String QUERY_DELETE = """
            DELETE FROM public.address
            WHERE id = ?;
            """;

    public AddressDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Address address) {
        try (
                PreparedStatement statement = connection.prepareStatement(QUERY_INSERT)
                ) {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getBuilding());
            statement.setString(5, address.getStage());
            statement.setString(6, address.getApartment());
            statement.setString(7, address.getDoorcode());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Address> read(int id) {
        try (
                PreparedStatement statement = connection.prepareStatement(QUERY_SELECT)
                ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Address address = null;
            if (resultSet.next()) {
                address = buildAddress(resultSet);
            }
            return Optional.ofNullable(address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Address> readAll() {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL)
                ) {
            List<Address> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildAddress(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Address address) {
        try (
                PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE)
                ) {
            statement.setInt(8, address.getId());
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getBuilding());
            statement.setString(5, address.getStage());
            statement.setString(6, address.getApartment());
            statement.setString(7, address.getDoorcode());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (
                PreparedStatement statement = connection.prepareStatement(QUERY_DELETE)
                ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Address buildAddress(ResultSet resultSet) throws SQLException {
        return new Address(
                resultSet.getInt("id"),
                resultSet.getString("country"),
                resultSet.getString("city"),
                resultSet.getString("street"),
                resultSet.getString("building"),
                resultSet.getString("stage"),
                resultSet.getString("apartment"),
                resultSet.getString("doorcode")
        );
    }
}
