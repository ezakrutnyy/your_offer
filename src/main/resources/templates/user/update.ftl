<#import "../macros/common.ftl" as c>

<@c.page>

    <h5 class="text-primary">Update user</h5>
    <hr/>

    <form action="/user/update/${user.id}" method="post">
        <input type="text" name="username" value="${user.username}" />
        <#list roles as role>
            <div>
                <label>
                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                </label>
            </div>
        </#list>
        <div>
            <label><input type="checkbox" name="active" ${user.active?string("checked", "")}>Active</label>
        </div>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>