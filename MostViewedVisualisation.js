google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawChart);

$("#clickhere").click(function () {
     $("#clickhere").hide();
    $.ajax({
        url: "MostViewedReport",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});

function createDataTable(jsonData) {
    var parsedData = $.parseJSON(jsonData);
    var data = new Array();
    var productNameArr = new Array();
    var viewCountArr = new Array();

    console.log(parsedData);
    var actualData = parsedData.myArrayList;
    data2 = ['ProductName', 'viewCount'];
    productNameArr.push(data2);    
    for(var i=0; i<actualData.length; i++) {
      console.log(actualData[i]);
        var productName = actualData[i].map.productName;
        var viewCount = actualData[i].map.viewCount;
        var data1 = [];
        data1.push(productName);
        data1.push(viewCount);
        productNameArr.push(data1);
     }
     console.log(productNameArr)
     drawChart(data, productNameArr);
}
function drawChart(data, productNameArr) {
  console.log(productNameArr);
  var chartData = google.visualization.arrayToDataTable(productNameArr);

  var materialOptions = {
    'width':1000,
    'height':1000,
    chart: {
      title: 'Products with views',
      subtitle: 'Based on number of Views'
    },
    hAxis: {
      title: 'View Count',
      minValue: 0,
    },
    vAxis: {
      title: 'Product Name'
    },
    bars: 'vertical',
    axes: {
      y: {
        0: {side: 'left'}
      }
    }
    };
  var materialChart = new google.charts.Bar(document.getElementById('chart_div'));
  materialChart.draw(chartData, materialOptions);
}