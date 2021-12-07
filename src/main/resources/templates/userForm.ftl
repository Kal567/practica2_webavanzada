<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MyMocky</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<script src="https://example.com/example-framework.js"
        integrity="sha384-oqVuAfXRKap7fdgcCY5uykM6+R9GqQ8K/uxy9rx7HNQlGYl1kPzQho1wx4JwY8wC"
        crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>


<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <a class="navbar-brand" href="/">MyMocky</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">${crearmock}<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mymocks">${mismocks}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/allmockys">${todoslosmocks}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/allusers">${usuarioss}</a>
            </li>
            <form action = "/logout" method="post">
                <div class="col text-center">
                    <button type="submit" class="btn btn-dark">${cerrarsesion}</button>
                </div>
            </form>
        </ul>
    </div>
</nav>
<br>
<br>
<div>
    <h1>${usuarioss}</h1>
</div>
<div class="container-fluid">
    <div class="shadow-sm p3 mb-5 bg-white rounded">
        <div class="border rounded" style="padding: 20px">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">${username}</th>
                    <th scope="col">${roless}</th>
                    <th scope="col">   </th>
                </tr>
                </thead>
                <tbody>
                <#if usuarios?size != 0>
                    <#list usuarios as usr>
                        <#if roles?size != 0>
                            <#list roles as rol>
                        <tr>
                            <td>${usr.username}</td>
                            <td>${rol}</td>
                            <th>
                                <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <form method="post" action="/edituser">
                                                <div class="modal-header text-center">
                                                    <h4 class="modal-title w-100 font-weight-bold">${editarusuario}</h4>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body mx-3">
                                                    <div class="form-group">
                                                        <label for="mat">${username}</label>
                                                        <input name="username" type="text" class="form-control" id="id" value="${usr.username}" readonly>
                                                    </div>
                                                    <div class="form-group">
                                                        <input  type="checkbox" id="admin" name="admin" unchecked>
                                                        <label for="admin">${haceradmin}</label>
                                                    </div>
                                                </div>
                                                <div class="modal-footer d-flex justify-content-center">
                                                    <button type="submit" class="btn btn-default">${editar}</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="text-center">
                                    <button id-stu="${usr.username}" id="edit" class="btn btn-primary" data-toggle="modal" data-target="#modalLoginForm">${editar}</button>
                                </div>
                            </th>
                            <th>
                                <form method="post" action="/deleteUser/${usr.username}">
                                    <button type="submit" class="btn btn-danger">${eliminar}</button>
                                </form>
                            </th>
                            </th>
                        </tr>
                            </#list>
                        </#if>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script type="text/javascript">
    $(document).ready(function(){
        var id;
        var action;
        $('.table').on('click', 'button#edit', function(){
            id = $(this).attr('id');
            $(".modal-title").text('Edit student');
            accion = "/update-student/"+id;
            var id = $(this).attr('id-stu');
            $("#id").attr('value', id);
            $("#id").prop('required',false);
        });
    });
</script>

</body>
</html>