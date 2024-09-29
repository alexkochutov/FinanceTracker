package org.financetracker.dao;

import org.financetracker.model.Address;
import org.financetracker.utils.DatabaseConnectionManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressDAOTest {

    private static AddressDAO addressDAO;

    private final Address ADDRESS = new Address(
            "Armenia",
            "Yerevan",
            "Margaryan street",
            "12",
            "5",
            "40",
            "NA"
    );

    @BeforeAll
    public static void setUp() throws SQLException {
        Connection connection = DatabaseConnectionManager.getTestConnection();
        addressDAO = new AddressDAO(connection);
    }

    @Test
    @Order(1)
    public void shouldCreate() {
        int expected = addressDAO.readAll().size() + 1;
        addressDAO.create(ADDRESS);
        assertEquals(expected, addressDAO.readAll().size());
    }

    @Test
    @Order(2)
    public void shouldRead() {
        ADDRESS.setId(addressDAO.readAll().getFirst().getId());
        Optional<Address> result = addressDAO.read(ADDRESS.getId());
        result.ifPresent(address -> assertEquals(ADDRESS, address));
    }

    @Test
    @Order(3)
    public void shouldUpdate() {
        ADDRESS.setId(addressDAO.readAll().getFirst().getId());
        ADDRESS.setDoorcode("40 C");

        addressDAO.update(ADDRESS);
        assertEquals(ADDRESS, addressDAO.readAll().getFirst());
    }

    @Test
    @Order(4)
    public void shouldDelete() {
        int expected = addressDAO.readAll().size() - 1;
        addressDAO.delete(addressDAO.readAll().getFirst().getId());
        assertEquals(expected, addressDAO.readAll().size());
    }
}