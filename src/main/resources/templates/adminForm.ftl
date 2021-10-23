<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mock Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a href="/#" class="navbar-brand" style="font-family: 'Arial'">
        Mockapi
    </a>
    </ul>
</nav>
<br>
<br>

<div class="container p-3 my-3 bg-dark text-white">
    <div class = "text-center">
        <h1>Crear administrador</h1>
    </div>


</div>


<div class="container pd-5">
    <div class="shadow-sm p3 mb-5 bg-white rounded">
        <div class="border" style="padding: 20px">
            <form action = "/registraradmin" method="post">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" id="nombre"  placeholder="Ingresar nombre">

                </div>
                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" class="form-control" id="password" placeholder="Ingresar Contraseña">
                </div>
                <br>

                <div class="col text-center">
                    <button type="submit" class="btn btn-primary" >Registrar</button>
                </div>

            </form>
        </div>

    </div>

</div>

</body>
</html>