<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMocky</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<br>


<div class = "text-center">
    <h1>Sign Up
    </h1>
</div>

<br>
<div class="container">
    <div class="shadow-sm p3 mb-5 bg-white rounded">
        <div class="border" style="padding: 20px">
            <form action = "/newuser" method="post">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text"  class="form-control" name="name" id="nombreUsuario"  placeholder="Michael Johnson">
                </div>
                <div class="form-group">
                    <label for="usuario">Username</label>
                    <input type="text"  class="form-control" name="username" id="nombre"  placeholder="thereal_mj1988">
                </div>
                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Ingresar Contraseña">
                </div>
                <br>
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary" >Sign Up</button>
                </div>


            </form>
        </div>

    </div>

</div>

</body>
</html>