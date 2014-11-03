Date.prototype.toFormattedString = function()
{
    var year = this.getFullYear();
    var month = this.getMonth() + 1;
    var date = this.getDate();

    var formattedDate = "{1}.{2}.{3}".replace( "{1}", date < 10 ? "0" + date : date )
                                     .replace( "{2}", month < 10 ? "0" + month : month )
                                     .replace( "{3}", year );

    return formattedDate;
}