<#macro offer path offer>
    <form action="${path}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="category">Category</label>
            <input type="text" class="form-control col-sm-6" name="category"
                   id="category" value="${offer.category!}"/>
        </div>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control col-sm-6" name="title"
                   id="title" value="${offer.title!}"/>
        </div>
        <div class="form-group">
            <label for="image">Image</label>
            <input type="file" class="form-control col-sm-6" name="file"
                   id="image" value="C:/suite/files/${offer.filename!}"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control col-sm-6" id="description" name="description" rows="3">${offer.description!}
            </textarea>
        </div>
        <div class="form-group">
            <label for="startPrice">Start price</label>
            <input type="number" class="form-control col-sm-6" name="startPrice"
                   id="startPrice" value="${offer.startPriceToStr!}"/>
        </div>
        <div class="form-group">
            <label for="totalDays">Count days</label>
            <input type="number" class="form-control col-sm-6" name="totalDays"
                   id="totalDays" value="${offer.totalDays!}"/>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</#macro>