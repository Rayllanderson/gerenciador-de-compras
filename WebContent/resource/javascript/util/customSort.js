function retnum(number) {
    number = number.substring(2);
    var num = number.replace('.', '');
    return num.replace(',', '.');
}



function customSort(a, b, sortOrder) {
    var order = sortOrder === 'desc' ? -1 : 1
    var aa = +((retnum(a) + ''))
    var bb = +((retnum(b) + ''))
    console.log(aa)
    if (aa < bb) {
        return order * -1
    }
    if (aa > bb) {
        return order
    }
    return 0
}


/*
function customSort(sortName, sortOrder, data) {
    var order = sortOrder === 'desc' ? -1 : 1
    data.sort(function (a, b) {
      var aa = +((retnum(a[sortName]) + ''))
      var bb = +((retnum(b[sortName]) + ''))
      if (aa < bb) {
        return order * -1
      }
      if (aa > bb) {
        return order
      }
      return 0
    })
  }
*/