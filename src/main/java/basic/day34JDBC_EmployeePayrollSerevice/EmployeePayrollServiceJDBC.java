package basic.day34JDBC_EmployeePayrollSerevice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeePayrollServiceJDBC {
	public static void main(String[] args) {
		connectionEshtablished();
		readEmployeePayroll();
		updateEmployeePayroll();
		showPayrollDataByName();
	}

	private static Connection getSqlConnection() {
		Connection conn = null;
		String dbHostUrl = "jdbc:mysql://localhost:3306/payroll_service";
		String userName = "root";
		String password = "Md78678678.";
		try {
			conn = DriverManager.getConnection(dbHostUrl, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	private static void connectionEshtablished() {
		Connection conn = getSqlConnection();
		if (conn != null) {
			System.out.println("Connection is eshtablished");
		}
	}

	private static void readEmployeePayroll() {
		System.out.println("Displaying all data of employee_payroll table");
		Connection conn = getSqlConnection();

		try {
			if (conn != null) {
				String readEmpPayroll = "SELECT * FROM employee_payroll";

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(readEmpPayroll);
				while (resultSet.next()) {
					Integer id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					Integer salary = resultSet.getInt(3);
					String date = resultSet.getString(4);
					String gender = resultSet.getString(5);
					String row = String.format(
							"User record: \n Id: %d, \n Name: %s,\n Salary: %d, \n Date: %s,  \n Gender: %s \n", id,
							name, salary, date, gender);
					System.out.println(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlException) {
					System.out.println(sqlException.getMessage());

				}
			}
		}

	}

	private static void updateEmployeePayroll() {
		System.out.println("Updating salary of Terisa ");
		Connection conn = getSqlConnection();
		if (conn != null) {
			String updateEmpPayroll = "UPDATE employee_payroll SET salary = ? WHERE name ='Terisa'";
			try {
				PreparedStatement preparedStatement = conn.prepareStatement(updateEmpPayroll);
				preparedStatement.setInt(1, 300000);
				int rowUpdated = preparedStatement.executeUpdate();
				if (rowUpdated > 0) {
					System.out.println("Data is Updated");
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException sqlException) {
						System.out.println(sqlException.getMessage());

					}
				}
			}
		}

	}

	private static void showPayrollDataByName() {
		System.out.println("Displaying payroll data by particular name");
		Connection conn = getSqlConnection();

		try {
			if (conn != null) {
				String readEmpPayroll = "SELECT * FROM employee_payroll WHERE name ='Mohsin'";

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(readEmpPayroll);
				while (resultSet.next()) {
					Integer id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					Integer salary = resultSet.getInt(3);
					String date = resultSet.getString(4);
					String gender = resultSet.getString(5);
					String row = String.format(
							"User record: \n Id: %d, \n Name: %s,\n Salary: %d, \n Date: %s,  \n Gender: %s \n", id,
							name, salary, date, gender);
					System.out.println(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlException) {
					System.out.println(sqlException.getMessage());

				}
			}
		}

	}

}
