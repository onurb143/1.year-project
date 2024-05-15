package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	public class DBConnection {
		private Connection connection = null;
		private static DBConnection dbConnection;
		private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		private static final String dbName = "DMA-CSD-S222_10461241";
		private static final String serverAddress = "hildur.ucn.dk";
		//private static final String serverAddress = "192.168.56.2";
		private static final int    serverPort = 1433;
		private static final String userName = "DMA-CSD-S222_10461241";
		private static final String password = "Password1!";
		
		private DBConnection() throws DataAccessException {
			// Cheat sheet for the printf() method, the format is also used in the
			// String.format() method
			// http://alvinalexander.com/programming/printf-format-cheat-sheet
			String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s;encrypt=false",
					serverAddress, serverPort, dbName, userName, password);
			try {
				Class.forName(driverClass);
				connection = DriverManager.getConnection(connectionString);
				System.out.println("Connection established");
			} catch (ClassNotFoundException e) {
				throw new DataAccessException(e, "Missing JDBC driver");
				// System.err.println("Could not load JDBC driver");
				// e.printStackTrace();

			} catch (SQLException e) {
				throw new DataAccessException(e, String.format("Could not connect to database %s@%s:%d user %s", dbName,
								serverAddress, serverPort, userName));
				// System.out.println("Connection string was: " + connectionString.substring(0,
				// connectionString.length() - password.length()) + "....");
				// e.printStackTrace();
			}
		}
		/**
		 * Returnerer en singleton-instanse af DBConnection, der håndterer databaseforbindelsen.
		 * Hvis der ikke allerede findes en eksisterende forbindelse, oprettes en ny.
		 * 
		 * @return en singleton-instanse af DBConnection
		 * @throws DataAccessException hvis der opstår en fejl under oprettelse af forbindelsen
		 * @Author gruppe 4
		 */
		public static synchronized DBConnection getInstance() throws DataAccessException {
			if (dbConnection == null) {
				dbConnection = new DBConnection();
			}
			return dbConnection;
		}

		/**
		 * Starter en database transaktion ved at deaktivere autocommit-funktionen på forbindelsen.
		 * Hvis der opstår en SQLException under forsøget på at ændre autocommit-indstillingen,
		 * kastes en DataAccessException med en passende fejlmeddelelse.
		 * 
		 * @throws DataAccessException hvis der opstår en fejl under forsøget på at starte transaktionen
		 * @Author gruppe 4
		 */
		public void startTransaction() throws DataAccessException {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new DataAccessException(e, "Could not start transaction.");
			}
		}
		
		/**
		 * Udfører en commit på den aktuelle database transaktion. Hvis der opstår en SQLException
		 * under forsøget på at udføre commit, kastes denne exception. Derefter aktiveres autocommit-funktionen
		 * igen på forbindelsen. Hvis der opstår en SQLException under forsøget på at aktivere autocommit,
		 * kastes en DataAccessException med en passende fejlmeddelelse.
		 * 
		 * @throws DataAccessException hvis der opstår en fejl under forsøget på at udføre commit eller aktivere autocommit
		 * @Author gruppe 4
		 */

		public void commitTransaction() throws DataAccessException {
			try {
				try {
					connection.commit();
				} catch (SQLException e) {
					throw e;
					// e.printStackTrace();
				} finally {
					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not commit transaction");
			}
		}

		/**
		 * Ruller den aktuelle database transaktion tilbage. Hvis der opstår en SQLException
		 * under forsøget på at rulle tilbage, kastes denne exception. Derefter aktiveres autocommit-funktionen
		 * igen på forbindelsen. Hvis der opstår en SQLException under forsøget på at aktivere autocommit,
		 * kastes en DataAccessException med en passende fejlmeddelelse.
		 * 
		 * @throws DataAccessException hvis der opstår en fejl under forsøget på at rulle transaktionen tilbage eller aktivere autocommit
		 * @Author gruppe 4
		 */
		public void rollbackTransaction() throws DataAccessException {
			try {
				try {
					connection.rollback();
				} catch (SQLException e) {
					throw e;
					// e.printStackTrace();
				} finally {
					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not rollback transaction");
			}
		}
		

		/**
		 * Udfører en INSERT-sætning i databasen og returnerer den genererede primærnøgleværdi.
		 * Den modtagne SQL-sætning udskrives til konsollen.
		 * Hvis der opstår en SQLException under forsøget på at udføre INSERT-sætningen,
		 * kastes en DataAccessException med en passende fejlmeddelelse.
		 * Hvis indsættelsen lykkes og der genereres en primærnøgle, returneres denne nøgleværdi.
		 * Hvis ingen primærnøgle genereres eller indsættelsen fejler, returneres -1.
		 * 
		 * @param sql den INSERT-sætning, der skal udføres
		 * @return den genererede primærnøgleværdi, -1 hvis ingen primærnøgle genereres eller indsættelsen fejler
		 * @throws DataAccessException hvis der opstår en fejl under forsøget på at udføre INSERT-sætningen
		 * @Author gruppe 4
		 */

		public int executeInsertWithIdentity(String sql) throws DataAccessException {
			System.out.println("DBConnection, Inserting: " + sql);
			int res = -1;
			try (Statement s = connection.createStatement()) {
				res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				if (res > 0) {
					ResultSet rs = s.getGeneratedKeys();
					rs.next();
					res = rs.getInt(1);
				}
				// s.close(); -- the try block does this for us now

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new DataAccessException(e, "Could not execute insert (" + sql + ").");
			}
			return res;
		}
		/**
		 * Udfører en INSERT-sætning i databasen ved hjælp af en forberedt erklæring (PreparedStatement) og returnerer den genererede primærnøgleværdi.
		 * Den forberedte erklæring (PreparedStatement) skal oprettes med yderligere argumentet PreparedStatement.RETURN_GENERATED_KEYS.
		 * Hvis der opstår en SQLException under forsøget på at udføre INSERT-sætningen,
		 * kastes en DataAccessException med en passende fejlmeddelelse.
		 * Hvis indsættelsen lykkes og der genereres en primærnøgle, returneres denne nøgleværdi.
		 * Hvis ingen primærnøgle genereres eller indsættelsen fejler, returneres -1.
		 * 
		 * @param ps den forberedte erklæring (PreparedStatement), der indeholder INSERT-sætningen og er konfigureret til at returnere de genererede nøgler
		 * @return den genererede primærnøgleværdi, -1 hvis ingen primærnøgle genereres eller indsættelsen fejler
		 * @throws DataAccessException hvis der opstår en fejl under forsøget på at udføre INSERT-sætningen
		 * @Author gruppe 4
		 */
		public int executeInsertWithIdentity(PreparedStatement ps) throws DataAccessException {
			// requires perpared statement to be created with the additional argument PreparedStatement.RETURN_GENERATED_KEYS  
			int res = -1;
			try {
				res = ps.executeUpdate();
				if (res > 0) {
					ResultSet rs = ps.getGeneratedKeys();
					rs.next();
					res = rs.getInt(1);
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new DataAccessException(e, "Could not execute insert");
			}
			return res;
		}

		public Connection getConnection() {
			return connection;
		}

		public void disconnect() {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
}
