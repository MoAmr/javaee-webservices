<html>
<body>
<h2>Jersey RESTful Web Application!</h2>
<p><a href="webapi/myresource">Jersey resource</a>
<p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
<p>

    HTML form Input: <br/>
    <form action="webapi/myresource/guest/salute"
          enctype="application/x-www-form-urlencoded" method="post">
<p>
    Salutation: <input type="text" name="salutation"/>
<p>
    <button type="submit">Say Hello to:</button>
    <input type="text" name="guest"/>

    </form>
</body>
</html>
