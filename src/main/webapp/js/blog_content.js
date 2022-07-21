function renderAuthor(user) {
    document.querySelector(".author-avatar").src = user.avatar
    document.querySelector(".navAvatar").src = user.avatar
    document.querySelector(".author").innerText = user.username
    document.querySelector(".git_repo").href = user.gitRepo
}

function renderCount(articleCount, typeCount) {
    document.querySelector('.articleCount').textContent = articleCount
    document.querySelector('.typeCount').textContent = typeCount
}

function renderArticleList(article) {
    document.querySelector(".contentTitle").textContent = article.title
    document.querySelector('.date').textContent = article.publishedAt
    document.querySelector(".content").textContent = article.content
}

var xhr = new XMLHttpRequest()
xhr.open("get", "/article-detail.json" + location.search)
xhr.onload = function () {
    var data = JSON.parse(this.responseText);

    if(!data.currentUser){
        location.assign("/login.html")
        return
    }

    renderAuthor(data.currentUser)
    renderCount(data.articleCount, data.typeCount)
    renderArticleList(data.article)
}
xhr.send()