<#import 'parts/common.ftl' as c>
<#import 'parts/login_form.ftl' as l>
Login page
<@c.page>
    <@l.login "/login"/>
    <a href="/registration">Add new user</a>
</@c.page>
