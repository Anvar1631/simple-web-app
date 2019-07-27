<#import 'parts/common.ftl' as c>
<#import  'parts/login_form.ftl' as l>

<@c.page>

    <@l.logout/>
    <span><a href="/user">User list</a></span>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите сообщение"/>
            <input type="text" name="tag" placeholder="Тэг"/>
            <input type="file" name="file">
            <button type="submit">Добавить сообщение</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div>Список сообщений</div>

    <form method="get" action="main">
        <input type="text" name="filter" value="${filter?ifExists}'ad'">
        <button type="submit">Find</button>
    </form>

    <div>${messageList}</div>
<#--    <span>${filter}</span>-->

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
            <div >
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>