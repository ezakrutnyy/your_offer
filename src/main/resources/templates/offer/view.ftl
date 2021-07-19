<#import "../macros/common.ftl" as c>

<@c.page>
    <h5 class="text-primary">${offer.title}</h5>
    <hr/>

    <div>
        <div>${offer.changeDate?string('dd.MM.yyyy')}</div>
        <div>${offer.category}</div>
        <div>${offer.description}</div>
        <div>${offer.currentPrice!}</div>
        <div>${offer.startPrice}</div>
        <div>${offer.dueDate?string('dd.MM.yyyy')}</div>
        <div>${offer.authorName}</div>
        <#if offer.filename??>
            <div>
                <img alt="" src="/img/${offer.filename}"/>
            </div>
        </#if>

    </div>
</@c.page>
