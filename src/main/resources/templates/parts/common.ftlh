<#macro page cssPath>
    <html>
    <head>
        <meta charset="UTF-8"/>
        <title>Справочник ЦБ РФ</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="shortcut icon" href="../../css/image/bank2.png">
        <link rel="stylesheet" href="${cssPath}">
        <link rel="stylesheet" href="../../css/styleButton.css">
        <link rel="stylesheet" href="../../css/styleBody.css">
    </head>
    <body>

    <#nested>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    </script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // добавляем обработчик клика на каждую строку таблицы
            $('table tr').click(function() {
                // удаляем класс active из всех строк таблицы
                $('table tr').removeClass('active');
                // добавляем класс active к выделенной строке
                $(this).addClass('active');
            });
        });
    </script>
    </body>
    </html>
</#macro>
