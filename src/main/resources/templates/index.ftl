<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMocky</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        textarea {
            resize: none;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <a class="navbar-brand" href="/">MyMocky</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">Crear Mock<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mymocks">Mis Mocks</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/allmockys">Todos los Mocks</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/allusers">Usuarios</a>
            </li>
            <form action = "/logout" method="post">
                <div class="col text-center">
                    <button type="submit" class="btn btn-dark">Cerrar Sesión</button>
                </div>
            </form>
        </ul>
    </div>
</nav>
<br>
<br>

<div class="container pb-5">
    <div class="shadow-sm p3 mb-5 bg-white rounded">
        <div class="border" style="padding: 20px">
            <form action = "/api/auth" method="post">
                <div class="form-row">

                    <div class="form-group col-md-6">
                        <label for="rutaEndpoint">Nombre del endpoint</label>
                        <input name="rutaEndpoint" type="text" class="form-control" id="rutaEndpoint" placeholder="Nombre">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="descripcion">Descripción</label>
                        <input name="descripcion" type="text" class="form-control" id="descripcion" placeholder="">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="key">
                            Header Key
                        </label>
                        <input name="key" type="text" class="form-control" id="key" placeholder="">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="metodoAcceso">
                            Método de Acceso
                        </label>
                        <select name="metodoAcceso" class="form-control" id="metodoAcceso" >
                            <option>
                                GET
                            </option>
                            <option>
                                POST
                            </option>
                            <option>
                                PUT
                            </option>
                            <option>
                                PATCH
                            </option>
                            <option>
                                DELETE
                            </option>
                            <option>
                                OPTIONS
                            </option>
                        </select>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="headers">
                            HTTP Headers
                        </label>
                        <input name="headers" type="text" class="form-control" id="headers" placeholder="{}">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="codigoRespuesta">
                            Código de respuesta
                        </label>
                        <select name="codigoRespuesta" class="form-control" id="codigoRespuesta" >
                            <!-- 1xx Informational -->
                            <option>
                                100 Continue
                            </option>
                            <option>
                                101 Switching Protocols
                            </option>
                            <option>
                                102 Processing
                            </option>


                            <!-- 2xx Success -->

                            <option>
                                200 OK
                            </option>
                            <option>
                                201 Created
                            </option>
                            <option>
                                202 Accepted
                            </option>
                            <option>
                                203 Non-Authoritative Information
                            </option>
                            <option>
                                204 No Content
                            </option>
                            <option>
                                205 Reset Content
                            </option>
                            <option>
                                206 Partial Content
                            </option>
                            <option>
                                207 Multi-Status
                            </option>
                            <option>
                                208 Already Reported
                            </option>
                            <option>
                                226 IM Used
                            </option>


                            <!-- 3xx Redirection -->

                            <option>
                                300 Multiple Choices
                            </option>
                            <option>
                                301 Moved Permanently
                            </option>
                            <option>
                                302 Found
                            </option>
                            <option>
                                303 See Other
                            </option>
                            <option>
                                304 Not Modified
                            </option>
                            <option>
                                305 Use Proxy
                            </option>
                            <option>
                                306 (Unused)
                            </option>
                            <option>
                                307 Temporary Redirect
                            </option>
                            <option>
                                308 Permanent Redirect
                            </option>


                            <!-- 4xx  Client Error -->

                            <option>
                                400 Bad Request
                            </option>
                            <option>
                                401 Unauthorized
                            </option>
                            <option>
                                402 Payment Required
                            </option>
                            <option>
                                403 Forbidden
                            </option>
                            <option>
                                404 Not Found
                            </option>
                            <option>
                                405 Method Not Allowed
                            </option>

                            <!-- finish adding the response codes -->
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="contentType">
                            Tipo de Contenido
                        </label>
                        <select name="contentType" class="form-control" id="contentType" >
                            <option>
                                application/json
                            </option>
                            <option>
                                application/x-www-form-urlencoded
                            </option>
                            <option>
                                application/xhtml+xml
                            </option>
                            <option>
                                application/xml
                            </option>
                            <option>
                                multipart/form-data
                            </option>
                            <option>
                                text/css
                            </option>
                            <option>
                                text/csv
                            </option>
                            <option>
                                text/html
                            </option>
                            <option>
                                text/json
                            </option>
                            <option>
                                text/plain
                            </option>
                            <option>
                                text/xml
                            </option>
                        </select>
                    </div>



                    <div class="form-group col-md-6">
                        <label for="delay">
                            Demora de respuesta
                        </label>
                        <input name="delay" type="text" class="form-control" id="delay" placeholder="0 segundos">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="response">
                            Cuerpo de Respuesta
                        </label>
                        <textarea rows="5" name="response"  class="form-control" id="response" placeholder="{



}"></textarea>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="tiempoExpiracion">
                            Tiempo de expiración
                        </label>
                        <select name="tiempoExpiracion" class="form-control" id="tiempoExpiracion" >
                            <option>
                                1 Mes
                            </option>
                            <option>
                                1 Semana
                            </option>
                            <option>
                                1 día
                            </option>
                            <option>
                                1 Hora
                            </option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <input  type="checkbox" id="jwt" name="jwt" unchecked>
                        <label for="jwt">¿Desea añadir más seguridad con JWT?</label>
                    </div>
                </div>
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary" >Crear</button>
                </div>
            </form>
        </div>

    </div>

</div>

</body>
</html>