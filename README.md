# ms-gestion-cuentas-banca
Microservicio que gestion los productos de cliente

1. Se trabajo con gitFlow para el despliegue entre ambientes
2. Diagrama de arquitectura del microservicio

![diagrama gestion-banca](https://github.com/diego3797/ms-gestion-cuentas-banca/assets/75541408/a7d47319-e36c-4ee2-b3fb-ee76f57621e7)

   
3. Se genero el proyecto mediante Api First, donde se mapeo todos los endpoint completos del api

![image](https://github.com/diego3797/ms-gestion-cuentas-banca/assets/75541408/fc33eb1f-b8ea-44c2-bff5-d67367b0c3fe)

Contrato: api_cuentas_banca_v1.yml
   
4. Se agregaron funcinoalidades:
   - CRUD completo de la colecction cliente
   - Operaciones de Deposito y Retiro
   - Operaciones de Transferencia entre cuentas propias y a terceros
   - Operaciones de Credito, consumo y pago.
   - Consultas de Productos:
      - Consulta de saldo de Cuenta de Ahorro o Credito
      - Consulta de saldo de todos los productos
      - Consulta de movimientos de un producto (cuenta de ahorro o credito)
   - Reporte de todos los productos de un cliente y Comisiones de por Producto
5. Se implemento la revisiones de checkStyle y Sonarqube
6. Se creo carpeta Data en la raiz con script de insercion de la coleccion cliente
7. Se agrego la collection postman en la raiz, con las funcionalidades del CRUD para la coleccion cliente   

