console.log("Reply Module......")
const replyService = (function () {
    function add(reply, callback, error) {
        console.log("add reply...")
        callRequest("POST", "../replies/new", reply, callback)
    }

    function getList(param, callback, error) {
        console.log("getList reply...")
        const bno = param.bno
        const page = param.page || 1
        callRequest("GET", "../replies/pages/" + bno + "/" + page + ".json", {}, callback)
    }

    function update(reply, callback, error) {
        console.log("update reply...")
        const rno = reply.rno
        callRequest("PUT", "../replies/" + rno, reply, callback, error)
    }

    function remove(param, callback, error) {
        console.log("remove reply...")
        const rno = param.rno
        callRequest("DELETE", "../replies/" + rno, {}, callback, error)
    }

    //24시간 이전 댓글은 날짜만 표시, 24시간 이내의 댓글은 시간으로 표시
    function displayTime(timeValue) {
        const today = new Date();
        const gap = today.getTime() - timeValue;
        const dateObj = new Date(timeValue);

        if (gap < (1000 * 60 * 60 * 24)) {
            const hh = dateObj.getHours();
            const mi = dateObj.getMinutes();
            const ss = dateObj.getSeconds();

            return [(hh > 9 ? "" : "0") + hh, ":", (mi > 9 ? "" : "0") + mi, ":", (ss > 9 ? "" : "0") + ss].join("")
        } else {
            const yy = dateObj.getFullYear()
            const mm = dateObj.getMonth() + 1
            const dd = dateObj.getDate()

            return [yy, "/", (mm > 9 ? "" : "0") + mm, "/", (dd > 9 ? "" : "0") + dd].join("")
        }
    }

    return {
        add: add,
        getList: getList,
        update: update,
        remove: remove,
        displayTime: displayTime
    }
})();

const callRequest = function (requestMethod, url, param, callback, error) {
    const httpRequest = new XMLHttpRequest()

    httpRequest.onreadystatechange = () => {
        //console.log(httpRequest)
        /*
            httpRequest.UNINITIALIZED == 0   객체만 생성되고 아직 초기화되지 않은 생태(OPEN메서드가 호출 되지 않음)
            httpRequest.LOADING == 1 	     OPEN 메서드가 호출되고 아직 SEND 메서드가 불리지 않은 상태
            httpRequest.LOADED == 2          SEND메서드가 불렸지만 STATUS와 헤더는 도착하지 않은 상태
            httpRequest.INTERACTIVE == 3     데이터의 일부를 받은 상태
            httpRequest.COMPLETED == 4       데이터를 전부 받은 상태
        */
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                const result = httpRequest.response;
                if (callback) {
                    callback(result)
                }
            } else {
                if (error) {
                    error(httpRequest.status)
                }
            }
        }
    }

    httpRequest.open(requestMethod, url, true)
    if (requestMethod === "GET") {
        httpRequest.responseType = "json"
    }
    httpRequest.setRequestHeader("Content-Type", "application/json; charset=utf-8")
    httpRequest.send(JSON.stringify(param))

}



