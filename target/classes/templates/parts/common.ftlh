<#macro page cssPath>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <title>Справочник ЦБ РФ</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="shortcut icon" href="../../css/image/bank3.png">
    <link rel="stylesheet" href="${cssPath}">
    <link rel="stylesheet" href="../../css/styleButton.css">
    <link rel="stylesheet" href="../../css/styleBody.css">
</head>
<body>
    <#nested>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</body>
</html>
</#macro>
