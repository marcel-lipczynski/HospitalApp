package com.szbd.hospital;

import lombok.extern.log4j.Log4j2;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.sql.*;
import java.util.List;
import java.util.Objects;



@Log4j2
@SpringBootApplication
public class HospitalApplication {



	private EntityManager entityManager;

	public HospitalApplication(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() throws SQLException {
		System.out.println("\n");
		EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager.getEntityManagerFactory();
		Connection connection = Objects.requireNonNull(info.getDataSource()).getConnection();

		CallableStatement stmt = connection.prepareCall("{ ? = call countTables()}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.execute();
		int numberOfTables = stmt.getInt(1);
		log.info("Liczba tabel w schemacie: " + numberOfTables);


		stmt.close();

		System.out.println("\n\n\n\n\n\n\n");

		


	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void init(){
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

}
