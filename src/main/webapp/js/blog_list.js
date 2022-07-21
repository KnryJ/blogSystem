// 1. request /json/article_list.json
// 2. update dom tree
function renderAuthor(currentUser) {
    document.querySelector('.author-avatar').src = currentUser.avatar
    document.querySelector(".navAvatar").src = currentUser.avatar
    document.querySelector('.author-username').textContent = currentUser.username
    document.querySelector('.author-git').href = currentUser.gitRepo
}

function renderCount(articleCount, typeCount) {
    document.querySelector('.article-count').textContent = articleCount
    document.querySelector('.type-count').textContent = typeCount
}

function renderArticleList(articleList) {
    console.log(articleList)
    var container = document.querySelector('.articleList')
    console.log(container)
    for (var i in articleList) {
        var article = articleList[i]

        var html = `<div class="blog">` +
            `<div class="title">${article.title}</div>` +
            `<div class="date">${article.publishedAt}</div>` +
            `<div class="desc">${article.summary}</div>` +
            `<a href="blog_content.html?aid=${article.aid}" class="detail">查看全文 &gt;&gt;</a>` +
        `</div>`

        container.innerHTML += html
    }
}

var xhr = new XMLHttpRequest()
xhr.open('get', '/article-list.json')
xhr.onload = function() {
    var data = JSON.parse(this.responseText)

    if(!data.currentUser){
        location.assign("/login.html")
        return
    }

    renderAuthor(data.currentUser)
    renderCount(data.articleCount, data.typeCount)
    renderArticleList(data.articleList)
}
xhr.send()