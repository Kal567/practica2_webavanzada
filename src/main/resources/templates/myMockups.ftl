<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CRUD - List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<script src="https://example.com/example-framework.js"
        integrity="sha384-oqVuAfXRKap7fdgcCY5uykM6+R9GqQ8K/uxy9rx7HNQlGYl1kPzQho1wx4JwY8wC"
        crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>


<nav class="navbar navbar-dark bg-dark">
    <a href="/#" class="navbar-brand" style="font-family: 'Arial'">
        Mockapi
    </a>

</nav>
<br>
<br>
<div>

</div>
<div class="container-fluid">
    <div class="shadow-sm p3 mb-5 bg-white rounded">
        <div class="border rounded" style="padding: 20px">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Matricula</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellido</th>
                    <th scope="col">Teléfono</th>
                    <th scope="col">         </th>
                </tr>
                </thead>
                <tbody>
                <#if estudiante?size != 0>
                    <#list estudiante as estu>
                        <tr>
                            <td>${estu.id}</td>
                            <td>${estu.matricula}</td>
                            <td>${estu.nombre}</td>
                            <td>${estu.apellido}</td>
                            <td>${estu.telefono}</td>
                            <th>
                                <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <form method="post" action="/modificar-estudiante">
                                                <div class="modal-header text-center">
                                                    <h4 class="modal-title w-100 font-weight-bold">Edit - STUDENT</h4>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body mx-3">
                                                    <div class="form-group">
                                                        <label for="mat">ID</label>
                                                        <input name="id" type="text" class="form-control" id="id" value="${estu.id}" readonly>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="mat">Matricula</label>
                                                        <input name="matricula" type="text" class="form-control" id="mat">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="nombre">Nombre</label>
                                                        <input name="nombre" type="text" class="form-control" id="nombre">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="apellido">Apellido</label>
                                                        <input name="apellido" type="text" class="form-control" id="apellido">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="telefono">Teléfono</label>
                                                        <input name="telefono" type="text" class="form-control" id="telefono">
                                                    </div>
                                                </div>
                                                <div class="modal-footer d-flex justify-content-center">
                                                    <button type="submit" class="btn btn-default">Edit</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="text-center">
                                    <button id-stu="${estu.id}" id="edit" class="btn btn-primary" data-toggle="modal" data-target="#modalLoginForm">Edit</button>
                                </div>
                            </th>
                            <th>
                                <form method="post" action="/delete-estudiante/${estu.id}">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </th>
                            </th>
                        </tr>
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