# MarsRover
Este proyecto resuelve la prueba técnica o Kata Mars Rover realizada durante el bootcamp de ATL Academy, desarrollada en el lenguaje Java con Spring Boot.

# API de MarsRover

Este proyecto es una API desarrollada en Spring Boot que implementa la lógica para controlar un Mars Rover en un mapa. Utiliza el patrón de diseño Singleton y está diseñado para responder a comandos y realizar acciones en el rover y el mapa. También incluye pruebas unitarias utilizando JUnit y Mockito.

## Endpoints y Funcionalidades

A continuación se detallan los endpoints disponibles y sus funcionalidades:

### Crear un Mapa Predeterminado

- `POST /map`: Crea un mapa predeterminado.
    - Retorna un nuevo mapa.

### Obtener el Mapa

- `GET /map`: Obtiene el mapa actual.
    - Retorna el mapa actual o un error 404 si no se ha creado un mapa.

### Crear un Obstáculo

- `POST /obstacle/{x}/{y}`: Crea un obstáculo en una ubicación específica del mapa.
    - Parámetros:
        - `x` (entero): Coordenada X del obstáculo.
        - `y` (entero): Coordenada Y del obstáculo.
    - Retorna el obstáculo creado o errores 404 o 409 en caso de problemas.

### Eliminar un Obstáculo

- `DELETE /obstacle/{x}/{y}`: Elimina un obstáculo en una ubicación específica del mapa.
    - Parámetros:
        - `x` (entero): Coordenada X del obstáculo.
        - `y` (entero): Coordenada Y del obstáculo.
    - Retorna un mensaje de éxito o un error 404 si no se encuentra el obstáculo.

### Crear un Mars Rover

- `POST /rover/{x}/{y}/{direction}`: Crea un Mars Rover en una ubicación específica del mapa con una dirección inicial.
    - Parámetros:
        - `x` (entero): Coordenada X del rover.
        - `y` (entero): Coordenada Y del rover.
        - `direction` (enumeración): Dirección inicial del rover (NORTH, EAST, SOUTH, WEST).
    - Retorna el rover creado o errores 404 o 409 en caso de problemas.

### Obtener el Mars Rover

- `GET /rover`: Obtiene el Mars Rover actual.
    - Retorna el rover actual o un error 404 si no se ha creado un rover o un error 404 en caso de problemas.

### Enviar Comandos al Mars Rover

- `POST /rover/{input}`: Envía una serie de comandos al Mars Rover.
    - Parámetros:
        - `input` (cadena): Comandos para el rover (por ejemplo, "LMMR").
    - Retorna la respuesta del rover a los comandos o un error 404 en caso de problemas.

**Nota:** La cuadrícula puede tener obstáculos. Si una secuencia de comandos determinada encuentra un obstáculo, el robot se desplaza hasta el último punto posible e informa del obstáculo anteponiendo O: a la cadena de posición que devuelve. Por ejemplo,  O:1:1:N  significaría que el robot ha encontrado un obstáculo en la posición (1, 2). De no encontrar obstáculo, solo se enviará una respuesta como: 1:1:N.

## Configuración

Para ejecutar esta API en tu entorno de desarrollo, sigue estos pasos:

1. Clona este repositorio.

   ```bash
   git clone https://github.com/DominicRG/MarsRover.git
2. Configura cualquier configuración adicional que necesites en application.properties.
3. La API estará disponible en `http://localhost:8080`.

## Pruebas Unitarias

Este proyecto incluye pruebas unitarias utilizando JUnit y Mockito. Puedes ejecutar estas pruebas utilizando tu IDE o mediante la línea de comandos.

## Autor
Dominic Roman.
- Email: romandominic30@gmail.com
- Linkedin: https://www.linkedin.com/in/dominic-roman-649332204/

## Agradecimientos

A ATL Academy por proporcionar el desafío de la Kata Mars Rover y a Lucas Moyano por impartir los conocimientos necesarios a lo largo del Bootcamp de Programación en Java, si desean aprender algo sobre Java o algun otro lenguaje de programación visitar su canal de youtube: https://www.youtube.com/@atl.academy

