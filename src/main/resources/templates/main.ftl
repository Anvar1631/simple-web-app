<#import 'parts/common.ftl' as c>
<#import  'parts/login_form.ftl' as l>

<@c.page>

    <@l.logout/>

    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Введите сообщение"/>
            <input type="text" name="tag" placeholder="Тэг"/>
            <button type="submit">Добавить сообщение</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div>Список сообщений</div>

    <form method="get" action="main">
        <input type="text" name="filter" value="${filter}">
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
        </div>
    <#else>
        No messages
    </#list>
</@c.page>