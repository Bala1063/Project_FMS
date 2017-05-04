function checksession() {
    document.getElementById('hidebody').style.display = "none";
    var x = document.cookie;
    var y = x.split(";");
    if (y.length > 1) {
        var z = y[0].split("=");
        if (z.length > 1) {
            if (z[1] === "")
            {
                alert("Session Expired! Please Login.");
                window.location = "Login.jsp";
            } else
            {

                document.getElementById('hidebody').style.display = "block";
            }
        }
    } else {
        alert("Session Expired! Please Login.");
        window.location = "Login.jsp";
    }
}
