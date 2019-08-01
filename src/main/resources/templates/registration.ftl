<#import 'parts/common.ftl' as c>
<#import 'parts/login_form.ftl' as l>

<@c.page>
    <div class="mt-1">
        Add new user
    </div>
    ${message?ifExists}
    <@l.login "/registration" true/>
</@c.page>