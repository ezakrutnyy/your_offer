<#import "../macros/common.ftl" as c>

<@c.page>

    <h5 class="text-primary">List of users</h5>
    <hr/>

    <div class="container bg-light">
        <div class="row p-3 border border-secondary font-weight-bold">
            <div class="col-sm">Username</div>
            <div class="col-sm">Roles</div>
            <div class="col-sm">Active</div>
            <div class="col-sm"></div>
            <div class="col-sm"></div>
        </div>
        <#list users as user>
            <div class="row p-3 border border-secondary">
                <div class="col-sm">${user.username}</div>
                <div class="col-sm">
                    <#list user.roles as role>
                        ${role}<#sep>, </#sep>
                    </#list>
                </div>
                <div class="col-sm">${user.active?string("Yes", "No")}</div>
                <div class="col-sm">
                    <a class="btn btn-secondary" href="/user/update/${user.id}">update</a>
                </div>
                <div class="col-sm">
                    <a class="btn btn-secondary" href="/user/delete/${user.id}">delete</a>
                </div>
            </div>
        </#list>
    </div>

</@c.page>