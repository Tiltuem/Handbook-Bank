<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Справочник ЦБ РФ</title>
</head>
<body>
<h1>Справочник ЦБ РФ</h1><hr>
<form action="/import" method="get">
    <h3>Тест</h3>
</form>
<h3>Импорт своего справочника БИК</h3>
<form action="/import/file" method="post" enctype="multipart/form-data">
    Прикрепите файл: <input type="file" name="fileXml"/><br><br>
    <input type="submit" value="Получить данные"/>
</form>
<h3>Поиск Справочника БИК на заданную дату</h3>
<form action="/import/date" method="post">
    Введите дату в формате (YYYY-mm-dd): <input type="text" name="date"/><br><br>
    <input type="submit" value="Получить данные"/>
</form>
</body>
</html>