### Escuela Colombiana de Ingeniería
### Ciclos de Vida del desarrollo de Software – CVDS
#### LABORATORIO 7: Tecnologías de persistencia - Frameworks de Persistencia - Introducción a MyBatis

Camilo Andrés Pichimata Cárdenas

Zuly Valentina Vargas Ramirez

## SECCIÓN I. - INTRODUCCIÓN A JDBC

![](img/MODEL1.png)

Datos de conexión:

    Url: jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba

    Driver: com.mysql.jdbc.Driver

    Usuario: bdprueba

    Contraseña: prueba2019

_Verifique por medio de un cliente SQL, que la información retornada por el programa coincide con la que se encuentra almacenada en base de datos:_

Para comparar los resultados se realizó la conexión a la base de datos bdprueba 
mediante DBeaver :

![](img/conexion_bdprueba.png)


1. Comprobación de valorTotalPedido( ) :

Sentencia SQL : 

`
"SELECT SUM(ORD_PRODUCTOS.precio * ORD_DETALLE_PEDIDO.cantidad) AS total FROM ORD_PRODUCTOS JOIN ORD_DETALLE_PEDIDO ON ORD_PRODUCTOS.codigo = producto_fk JOIN ORD_PEDIDOS ON ORD_DETALLE_PEDIDO.pedido_fk = ORD_PEDIDOS.codigo WHERE ORD_PEDIDOS.codigo = " + Integer.toString(codigoPedido); `

+ Resultado obtenido con el método implementado:

![](img/valorTotalPedidoCod.png) 

Confirmación del resultado:


![](img/valorTotalPedidoBD.png) 

2. Comprobación de nombresProductosPedido( ) :

Sentencia SQL : 

`
"SELECT nombre FROM ORD_PRODUCTOS JOIN ORD_DETALLE_PEDIDO ON ORD_DETALLE_PEDIDO.producto_fk = ORD_PRODUCTOS.codigo JOIN ORD_PEDIDOS ON ORD_DETALLE_PEDIDO.pedido_fk =  ORD_PEDIDOS.codigo WHERE ORD_PEDIDOS.codigo = " + Integer.toString(codigoPedido); 
`

+ Resultado obtenido con el método implementado:

![](img/nombresProductosPedidoCod.png)

Confirmación del resultado:

![](img/nombresProductosPedidoBD.png)


3. Comprobación de nombresProductosPedido( ):

Sentencia SQL : 

`
"INSERT INTO ORD_PRODUCTOS(codigo,nombre,precio) VALUES(" + Integer.toString(codigo) +", '"+ nombre + "',"+ precio + ")";
`
![](img/registrarNuevoProductoBD.png)

## SECCIÓN II. - INTRODUCCIÓN A MYBATIS

![](img/MODEL.png)

**resultMaps iniciales: (Puntos 1 - 5):**

![](img/resultMapInicial.png)


 **resultMaps con prefijos determinados : (Puntos 6):**

 ![](img/resultMapPrefijos.png)


**Prueba**:

_7. Use el programa de prueba suministrado (MyBatisExample) para probar cómo a través del 'mapper' generado por MyBatis, se puede consultar un Cliente:_

Consultar Clientes  con _consultarClientes_ :

 ![](img/consultarClientes.png)


 ## Parte II (para el Miércoles)

 _2.Verifique el funcionamiento haciendo una consulta a través del 'mapper' desde MyBatisExample:_

 Consultar Cliente con id=8 y _consultarCliente_ :

   ![](img/consultarCliente-8.png)


_3.Configure en el XML correspondiente, la operación agregarItemRentadoACliente. Verifique el funcionamiento haciendo una consulta a través del 'mapper' desde MyBatisExample._

**Mapper:**
 
 ![](img/mapperItemRentado.png)
 
 **Prueba:**

 Ingresamos el item 92 al cliente con id 1478822:

 ![](img/pruebaItemRentadoCliente.png)

Prueba del ingreso en la base de datos:

 ![](img/pruebaBDItemRentadoCliente.png)



_4.Configure en el XML correspondiente (en este caso ItemMapper.xml) la operación 'insertarItem(Item it)._

**Mapper:**
![](img/pruebaInsertItem.png)

 **Prueba:**

Ingresamos un nuevo Item llamado "Oxigeno" con fecha de lanzamiento 2021-05-12, de genero "Ciencia ficción":

![](img/pruebaItem.png)

Prueba del ingreso en la base de datos:


![](img/pruebaItemBD.png)

_5.Configure en el XML correspondiente (de nuevo en ItemMapper.xml) las operaciones 'consultarItem(int it) y 'consultarItems()' de ItemMapper. En este caso, tenga adicionalmente en cuenta:_

**consultarItems():**

 **Prueba:**

![](img/consultarItems.png)


**consultarItem(20):**

Consultamos el anterior item ingresado el cual tiene como id el número 20:

 **Prueba:**


![](img/consultarItem20.png)
