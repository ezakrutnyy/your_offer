<#import "../macros/common.ftl" as c>

<@c.page>

    <h5 class="text-primary">Offers</h5>
    <hr/>

    <form action="offer" method="get">
        <div class="container">
            <div class="row p-3">
                <div class="col-sm-1">
                    <span>Category</span>
                </div>
                <div class="col-sm-3">
                    <input class="w-100" type="text" name="filter" id="filter" value="${filter}"/>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="btn btn-primary w-100">Filter</button>
                </div>
            </div>
        </div>
    </form>


    <#if offers?has_content>
        <div class="container bg-light">
            <div class="row p-3 border border-secondary font-weight-bold">
                <div class="col-sm">Title</div>
                <div class="col-sm">Category</div>
                <div class="col-sm">Date</div>
                <div class="col-sm">Due Date</div>
                <div class="col-sm">Description</div>
                <div class="col-sm">Price</div>
                <div class="col-sm">Author</div>
                <div class="col-sm"></div>
                <div class="col-sm"></div>
            </div>
            <#list offers as offer>
                <div class="row p-3 border border-secondary">
                    <div class="col-sm"><a  href="/offer/view/${offer.id}">${offer.title}</a></div>
                    <div class="col-sm">${offer.category}</div>
                    <div class="col-sm">${offer.changeDate?string('dd.MM.yyyy')}</div>
                    <div class="col-sm">${offer.dueDate?string('dd.MM.yyyy')}</div>
                    <div class="col-sm">${offer.description}</div>
                    <div class="col-sm">
                        <#if offer.currentPrice??>
                            ${offer.currentPrice}
                        <#else>
                            ${offer.startPrice}
                        </#if>
                    </div>

                    <div class="col-sm">${offer.authorName}</div>

                    <div class="col-sm">
                        <a class="btn btn-secondary" href="/offer/update/${offer.id}">update</a>
                    </div>
                    <div class="col-sm">
                        <a class="btn btn-secondary" href="/offer/delete/${offer.id}">delete</a>
                    </div>
                </div>

            </#list>
        </div>
    <#else>
        No offer item...
    </#if>

    <br/>
    <a class="btn btn-secondary" href="/offer/create">Add new offer</a>
</@c.page>