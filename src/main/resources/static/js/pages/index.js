var DIRECTION = {
        LEFT: "LEFT",
        RIGHT: "RIGHT",
        DEFAULT: "DEFAULT"
    },
    GEAR = {
        I: "I",
        II: "II",
        III: "III",
        IV: "IV",
        DEFAULT: "DEFAULT",
        R: "R"
    },
    gearBox = new GearBox();

$(document).keypress(function (event) {

    var keycode = (event.keyCode ? event.keyCode : event.which);

    switch (keycode) {
        case 40:
            goReverse();
            break;
        case 32:
            //space key
            stop();
            break;
        case 38:
            //alert('You pressed "up" key');
            goForward();
            break;
        case 37:
            //alert('You pressed "left" key');
            goLeft();
            break;
        case 39:
            //alert('You pressed "right" key');
            goRight();
            break;
        case 49:
            //alert('You pressed "1" key');
            setFirstGear();
            break;
        case 50:
            //alert('You pressed "2" key');
            setSecondGear();
            break;
        case 51:
            //alert('You pressed "3" key');
            setThirdGear();
            break;
        case 52:
            //alert('You pressed "4" key');
            setFourthGear();
            break;
    }

});


function startBroadcast() {
    ajaxCallGet('startbroadcast');
    setTimeout(loadDashboardData(), 1000);
    location.reload();
}

function stopBroadcast() {
    ajaxCallGet('stopbroadcast');
    setTimeout(loadDashboardData(), 1000);
    location.reload();
}

function loadDashboardData() {
    var dashboardData = ajaxCallGet('dashboardData');
    if (dashboardData.cameraEnabled != null) {
        document.getElementById("webboradcast-div").style.display = null;
        if (dashboardData.cameraEnabled ) {
            document.getElementById("startbradcast").style["display"] = "none";
            document.getElementById("stopbradcast").style.display = null;
        } else {
            document.getElementById("stopbradcast").style["display"] = "none";
            document.getElementById("startbradcast").style.display = null;
        }
    }
    document.getElementById("streaming").src = dashboardData.broadcastURL;
}

function GearBox() {
    var currentDirection = DIRECTION.DEFAULT,
        currentGear = GEAR.DEFAULT;
    this.setDirection = function (direction) {
        currentDirection = direction;
        accelerate();
    };

    this.setDirectionAndGear = function (direction,gear) {
        currentDirection = direction;
        currentGear = gear;
        accelerate();
    };

    this.setGear = function (gear) {
        currentGear = gear;
        accelerate();
    };

    this.getDirection = function () {
        return currentDirection;
    };

    this.getGear = function () {
        return currentGear;
    };

    return this;
}

function stop() {
    gearBox.setDirectionAndGear(DIRECTION.DEFAULT,GEAR.DEFAULT);
}

function goForward() {
    gearBox.setDirection(DIRECTION.DEFAULT);
}

function goLeft() {
    gearBox.setDirection(DIRECTION.LEFT);
}

function goReverse() {
    gearBox.setGear(GEAR.R);
}

function goRight() {
    gearBox.setDirection(DIRECTION.RIGHT);
}

function setFirstGear() {
    gearBox.setGear(GEAR.I);
}

function setSecondGear() {
    gearBox.setGear(GEAR.II);
}

function setThirdGear() {
    gearBox.setGear(GEAR.III);
}

function setFourthGear() {
    gearBox.setGear(GEAR.IV);
}


var selectors = {
    forwardButton: $('#forward_button'),
    rightButton: $('#right_button'),
    leftButton: $('#left_button'),

    firstGearButton: $('#first_gear_button'),
    secondGearButton: $('#second_gear_button'),
    thirdGearButton: $('#third_gear_button'),
    fourthGearButton: $('#fourth_gear_button'),
    stopGearButton: $('#stop_gear_button'),
    reverseGearButton: $('#reverse_gear_button')
};


function initEvents() {
    selectors.forwardButton.click(function () {
        goForward();
    });

    selectors.leftButton.click(function () {
        goLeft();
    });

    selectors.rightButton.click(function () {
        goRight();
    });

    selectors.reverseGearButton.click(function () {
        goReverse();
    });

    selectors.firstGearButton.click(function () {
        setFirstGear();
    });

    selectors.secondGearButton.click(function () {
        setSecondGear();
    });

    selectors.thirdGearButton.click(function () {
        setThirdGear();
    });

    selectors.fourthGearButton.click(function () {
        setFourthGear();
    });

    selectors.stopGearButton.click(function () {
        stop();
    });
}

function accelerate() {
    ajaxCallGetAsync('/accelerate?gear=' + gearBox.getGear() + "&direction=" + gearBox.getDirection());
}