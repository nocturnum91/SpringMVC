<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h1>UPLOAD WITH HTTPREQUEST</h1>
<div class="uploadDiv">
    <input type="file" name="uploadFile" multiple="">
</div>
<div>
    <button id="uploadBtn">Upload</button>
</div>
<div class="uploadResult">
    <ul></ul>
</div>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>--%>
<script type="text/javascript">

    const uploadBtn = document.getElementById("uploadBtn")
    const uploadDiv = document.querySelector(".uploadDiv")
    const input = uploadDiv.querySelector("input")
    const inputClone = "<input type='file' name='uploadFile' multiple=''>"

    uploadBtn.addEventListener("click", function () {

        const formData = new FormData()
        const files = document.querySelector("input[name='uploadFile']").files

        for (let i = 0; i < files.length; i++) {
            if (!checkExtension(files[i].name, files[i].size)) {
                return false;
            }
            formData.append("uploadFile", files[i])
        }

        const httpRequest = new XMLHttpRequest()
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                    const result = httpRequest.response
                    console.log(result)
                    showUploadedFile(result)
                    uploadDiv.innerHTML = inputClone

                } else {
                    console.log(httpRequest.status)
                }
            }
        }

        httpRequest.open("POST", "uploadHttp", true)
        httpRequest.responseType = "json"
        //httpRequest.setRequestHeader("Content-Type", "application/json; charset=utf-8")
        httpRequest.send(formData)

    })

    const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$")
    const maxSize = 5242880; //5MB
    function checkExtension(fileName, fileSize) {
        if (fileSize >= maxSize) {
            alert("파일 사이즈 초과");
            return false;
        }
        if (regex.test(fileName)) {
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }
        return true;
    }

    const uploadResult = document.querySelector(".uploadResult ul")

    function showUploadedFile(uploadFileArr) {
        let str = "";
        uploadFileArr.forEach(function (obj) {

            console.log(obj)
            str += "<li>" + obj.fileName + "</li>"
        })

        uploadResult.insertAdjacentHTML("beforeend",str)
    }


</script>
</body>
</html>
