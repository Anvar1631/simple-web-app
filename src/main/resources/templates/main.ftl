<#import 'parts/common.ftl' as c>


<@c.page>
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="main">
            <input class="form-control" type="text" name="filter" value="${filter?ifExists}"
                   placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>

    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false">
        Add new message
    </a>

    <div class="collapse" id="collapseExample">
        <div class="form-group col-md-6 mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input class="form-control" type="text" name="text" placeholder="Введите сообщение"/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="tag" placeholder="Тэг"/>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="file" placeholder="choose file">
                        <label class="custom-file-label" for="file"></label>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary ml-2" type="submit">Добавить сообщение</button>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </div>
    </div>

    <div>${messageList}</div>
<#--    <span>${filter}</span>-->

    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <b>${message.id}</b>
                    <span>${message.text!}</span>
                    <i>${message.tag!}</i>
                </div>
                <div class="card-footer text-muted">
                    <strong>${message.authorName!}</strong>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>