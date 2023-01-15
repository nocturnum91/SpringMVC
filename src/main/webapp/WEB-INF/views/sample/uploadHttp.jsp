<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <style>
        .uploadResult {
            width: 100%;
            background-color: #55595c;
        }

        .uploadResult ul {
            display: flex;
            flex-flow: row;
            justify-content: center;
            align-items: center;
        }

        .uploadResult ul li {
            list-style: none;
            padding: 10px;
        }

        .uploadResult ul li svg {
            width: 20px;
        }

        .bigPictureWrapper {
            position: absolute;
            display: none;
            justify-content: center;
            align-items: center;
            top: 0%;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.7);
        }

        .bigPicture {
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .bigPicture img {
            width: 600px;
        }
    </style>
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
<div class="bigPictureWrapper">
    <div class="bigPicture"></div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
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
            alert("파일 사이즈 초과")
            return false;
        }
        if (regex.test(fileName)) {
            alert("해당 종류의 파일은 업로드할 수 없습니다.")
            return false;
        }
        return true;
    }

    const uploadResult = document.querySelector(".uploadResult ul")

    function showUploadedFile(uploadFileArr) {
        let str = ""
        uploadFileArr.forEach(function (obj) {
            if (!obj.image) {
                const fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.fileName + "_" + obj.uuid + "." + obj.extension)
                str += "<li><a href='download?fileName=" + fileCallPath + "'>"
                    + "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-paperclip' viewBox='0 0 16 16'><path d='M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z'/></svg>"
                    + obj.fileName + "</a>"
                    + "<span data-file=\'" + fileCallPath + "\' data-type='file'> x </span></li>"
            } else {
                const fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.fileName + "_" + obj.uuid + "." + obj.extension)
                const originPath = obj.uploadPath + "/" + obj.fileName + "_" + obj.uuid + "." + obj.extension
                str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\"><img src='display?fileName=" + fileCallPath + "'></a>"
                    + "<span data-file=\'" + fileCallPath + "\' data-type='image'> x </span></li>"
            }
        })
        uploadResult.insertAdjacentHTML("beforeend", str)
    }

    const bigPictureWrapper = document.querySelector(".bigPictureWrapper")
    const bigPicture = document.querySelector(".bigPicture")

    function showImage(fileCallPath) {
        console.log(fileCallPath)

        bigPictureWrapper.style.display = "flex"

        const str = "<img src='display?fileName=" + encodeURI(fileCallPath) + "'>"
        bigPicture.insertAdjacentHTML("beforeend", str)
        bigPicture.animate({width: '100%', height: '100%'}, 1000)
    }

    bigPictureWrapper.addEventListener("click", function () {
        bigPicture.animate({width: '0%', height: '0%'}, 1000)
        setTimeout(() => {
            bigPictureWrapper.style.display = "none"
        }, 1000)
    })


    uploadResult.addEventListener("click", function (e) {
        console.log(e)

        if (e.target.nodeName === "SPAN") {

            const targetFile = e.target.dataset.file
            const type = e.target.dataset.type

            const data = new URLSearchParams()
            data.set("fileName", targetFile)
            data.set("type", type)

            // $.ajax({
            //     url: 'deleteFile',
            //     data : data,
            //     dataType: 'text',
            //     type: 'POST',
            //     success: function (result){
            //         console.log(result)
            //     }
            // })

            const httpRequest = new XMLHttpRequest()
            httpRequest.onreadystatechange = () => {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        const result = httpRequest.response
                        console.log(result)

                    } else {
                        console.log(httpRequest.status)
                    }
                }
            }

            httpRequest.open("POST", "deleteFile", true)
            httpRequest.dataType = "text"
            // httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            // //httpRequest.setRequestHeader("Content-Type", "application/json; charset=utf-8")
            httpRequest.send(data)

        }
    })


</script>
</body>
</html>
