/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        //Crear el mapper y usarlo: 
        ClienteMapper cm= sqlss.getMapper(ClienteMapper.class);
        //Consultar todos los clientes:
        //System.out.println(cm.consultarClientes());
        System.out.println("***********************************************************************************");
        /*
         * //Consultar un cliente con id 8:
        System.out.println(cm.consultarCliente(8));
        System.out.println("***********************************************************************************");
        */
        //Insertar item rentado: 
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			//Fechas:
			Date fechaInicial = formato.parse("2021-10-06");
			Date fechaFinal= formato.parse("2021-10-06");
			java.sql.Date sqlFechaInicial = new java.sql.Date(fechaInicial.getTime());
	        java.sql.Date sqlFechaFinal = new java.sql.Date(fechaFinal.getTime());
	        
	        cm.agregarItemRentadoACliente(1478822, 92, sqlFechaInicial, sqlFechaFinal);     
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sqlss.commit();
        sqlss.close();           
    }
}
