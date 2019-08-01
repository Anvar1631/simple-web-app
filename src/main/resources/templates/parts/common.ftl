<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Simple Web app</title>
        <link rel="stylesheet" href="/static/style/common.css"/>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <#--        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">-->
        <link rel="stylesheet" href="/static/lib/bootstrap-4.3.1-dist/css/bootstrap.min.css">

    </head>
    <body>
    <#include "navbar.ftl">
    <div class="container mt-5">
        <#nested>
    </div>
    <script src="/static/lib/jquery-3.4.1.js"></script>
    <script src="/static/lib/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>