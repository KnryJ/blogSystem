function renderAuthor(user) {
    document.querySelector(".navAvatar").src = user.avatar
}

var xhr = new XMLHttpRequest()
xhr.open("get", "/article-list.json")
xhr.onload = function () {
    var data = JSON.parse(this.responseText);
    if(!data.currentUser){
        location.assign("/login.html")
        return
    }
    renderAuthor(data.currentUser)
}
xhr.send()