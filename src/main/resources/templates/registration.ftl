<#import 'parts/common.ftl' as c>
<#import 'parts/login_form.ftl' as l>

<@c.page>
    Add new user
    ${message}
    <@l.login "/registration"/>
</@c.page>