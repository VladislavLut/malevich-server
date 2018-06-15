$("document").ready(function () {
    Reservation();
    var Time = new Date();
    var hours = addZero(Time.getHours());
    var time = document.getElementById("time").value = hours + ':00';
    document.getElementById("date").min = document.getElementById("date").value;
    console.log(time);
});

async function Reservation() {
    var Date = document.getElementById("date").value;
    // var password = document.getElementById("entry-password").value;
    try {
        await startSession();
        // const response = await getUser();
        // const user = await response.json();
        await showReservedTable(Date);
    } catch (error) {
        console.error(error);
    }

    function startSession() {
        return fetch("/session/start", {credentials: "same-origin"});
    }

    function showReservedTable(Date) {
        return fetch('/reserved/'+Date+'/date',
            {
                headers: {'Content-Type': 'application/json'},
                credentials: "same-origin"})
            .then((response) => { return response.text();})
        .then((res) => { console.log(res);resObj = JSON.parse(res); console.log(resObj);return resObj;})
        .then((resObj) => { blockRadioButton(resObj);});
    }

}

function UpdateshowReservedTable(Date) {
    var Date = document.getElementById("date").value;
    return fetch('/reserved/'+Date+'/date',
        {
            headers: {'Content-Type': 'application/json'},
            credentials: "same-origin"})
        .then((response) => { return response.text();})
.then((res) => { console.log(res);resObj = JSON.parse(res); console.log(resObj);return resObj;})
.then((resObj) => { blockRadioButton(resObj);});
}

function blockRadioButton(resObj) {
    for(var x = 1; x < 22; x++){
        var flag = false
        var currentRB = document.getElementById('radio'+x).id;
        var currentTime = document.getElementById('time').value + ':00';
        console.log(currentTime);
        for (var y = 0; y <resObj.length; y++){
            var tempID = 'radio'+resObj[y]['tableItem'];
            var tempTIME = resObj[y]['time'];
            if (currentRB == tempID && currentTime == tempTIME){
                document.getElementById('radio'+x).disabled = true;
                console.log('radio'+x+' is blocked');
                flag = true;
            }
        }
        if(flag != true) {
            $("#my_select").append($('<option value="' + x + '">' + x + ' столик</option>'));
        }
    }
}

function unlockRadioButton() {
    $('input[class="rb"]').prop('disabled', false);
    $("#my_select").empty();
    console.log('all RB is unlocked!');

}


function changed_date() {
    var Date = document.getElementById("date").value;
    console.log(changed_date);
    unlockRadioButton();
    try{
        UpdateshowReservedTable(Date);
        setOptionToSelect()
    } catch (error) {
        console.error(error);
    }
    console.log("Date is changed!")
}

function changed_time() {
    var Time = document.getElementById("time").value;
    console.log(changed_time);
    unlockRadioButton();
    try{
        UpdateshowReservedTable(Date);
        setOptionToSelect()
    } catch (error) {
        console.error(error);
    }
    console.log("Time is changed!")
}

function setOptionToSelect() {
    var z = $('input[name=option]:checked').val();
    console.log(z)
    $("#my_select [value="+z+"]").attr("selected", "selected");
}

function setRadioButtonFromSelect() {
    var y = $("#my_select option:selected").val();
    console.log(y)
    $('#radio'+y).prop('checked', true);
}


function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}



