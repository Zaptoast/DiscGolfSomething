window.addEventListener('load', function(e) {
  console.log('document loaded');
  init();
});

function init() {
  document.courseForm.lookup.addEventListener('click', function(event) {
    event.preventDefault();
    var courseId = document.courseForm.courseId.value;
    if (!isNaN(courseId) && courseId > 0) {
      getCourse(courseId);
    }
  })
  document.makeCourse.splork.addEventListener('click', addcourse);
  var button = document.getElementById('getAll');
  button.addEventListener('click', getCourses);
}

function displayAll(courses) {
  var courseList = document.getElementById('listCourses');
  clearAll();
  var ul = document.createElement('ul');
  courseList.appendChild(ul);

  for (var i = 0; i < courses.length; i++) {
    var course = courses[i];
    var li = document.createElement('li');
    li.textContent = course.name;

    li.id = course.id;
    ul.appendChild(li);
    li.addEventListener('click', function(e){
      getCourse(e.target.id);
    });
    var button = document.createElement('button');
    button.textContent = 'Delete';
    button.id = course.id;
    li.appendChild(button);
    button.addEventListener('click', function(e){
      deleteCourse(course);
    });
  }

}

function deleteCourse(course){
  clearAll();
  var xhr = new XMLHttpRequest();
  xhr.open('DELETE', 'api/courses/' + course.id, true);

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status < 400) {
      // var course = JSON.parse(xhr.responseText);
      var dataDiv = document.getElementById('courseData');
      dataDiv.textContent = course.name + ' successfully deleted';
    }
    if (xhr.readyState != 4 && xhr.status >= 400) {
      courseData.textContent = 'Course not found';
    }
  };

  xhr.send(null);
}

function getCourses() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/courses', true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status < 400) {
      var courses = JSON.parse(xhr.responseText);
      displayAll(courses);
    }
    if (xhr.readyState != 4 && xhr.status >= 400) {
      courseData.textContent = 'Something went wrong';
    }
  };
    xhr.send(null);
}

function getCourse(courseId) {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/courses/' + courseId, true);

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status < 400) {
      var course = JSON.parse(xhr.responseText);
      displayCourse(course);
      if(course.address != null){
      displayAddress(course.address);
      }else{
      // display add address button
      }
    }
    if (xhr.readyState != 4 && xhr.status >= 400) {
      courseData.textContent = 'Course not found';
    }
  };

  xhr.send(null);
}

function displayCourse(course) {
  var dataDiv = document.getElementById('courseData');
  clearAll();
  var name = document.createElement('h1');
  name.textContent = 'Course Name: ' + course.name;
  dataDiv.appendChild(name);
  var description = document.createElement('blockquote');
  description.textContent = 'Description: ' + course.description;
  dataDiv.appendChild(description);
  var div = document.createElement('div');
  div.textContent = 'Course length: ' + course.length;
  dataDiv.appendChild(div);
  var edit = document.createElement('button');
  edit.textContent = 'edit';
  edit.id = course.id;
  edit.addEventListener('click', function(e){
    showEditCourse(course);
  });
  dataDiv.appendChild(edit);
}

function showEditCourse(course){
  var dataDiv = document.getElementById('courseData');
  dataDiv.textContent = '';
  console.log(course);
  var form = document.createElement('form');
  form.id = 'editCourse';
  dataDiv.appendChild(form);
  var name = document.createElement('h3');
  name.textContent = 'Course Name:';
  form.appendChild(name);
  var input = document.createElement('input');
  input.type = 'text';
  input.name = 'name';
  input.value = course.name;
  form.appendChild(input);
  form.appendChild(document.createElement('br'));
  var description = document.createElement('h3');
  description.textContent = 'Course Description:';
  form.appendChild(description);
  var textArea = document.createElement('textarea');
  textArea.rows = 4;
  textArea.cols = 100;
  textArea.name = 'description';
  textArea.textContent = course.description;
  textArea.form = 'editCourse';
  form.appendChild(textArea);
  form.appendChild(document.createElement('br'));
  var length = document.createElement('h3');
  length.textContent = 'Course Length:';
  form.appendChild(length);
  var input = document.createElement('input');
  input.type = 'number';
  input.name = 'length';
  input.value = course.length;
  form.appendChild(input);
  form.appendChild(document.createElement('br'));
  var submit = document.createElement('input');
  submit.type = 'submit';
  submit.name = 'submit';
  submit.value = 'submit';
  form.appendChild(submit);
  submit.addEventListener('click', function(e){
    e.preventDefault();
    editCourse(course);
  });

}

function editCourse(course){

  var xhr = new XMLHttpRequest();

  xhr.open('Put', 'api/courses/' + course.id);
  xhr.setRequestHeader("content-type", "application/json");

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status < 400) {
        let courseObject = JSON.parse(xhr.responseText);
        getCourse(courseObject.id);
      } else {
        document.getElementById('courseData').textContent = 'Course Not Found';
      }
    }
  };
  var form = document.getElementById('editCourse');
  let courseObject = {
    name: form.name.value,
    description: form.description.value,
    length: form.length.value,
    address: course.address
  };
  xhr.send(JSON.stringify(courseObject));
}

function displayAddress(address) {
  var addressData = document.getElementById('addressData');
  addressData.textContent = '';
  var div = document.createElement('div');
  div.textContent = 'Street : ' + address.street;
  addressData.appendChild(div);
  div = document.createElement('div');
  div.textContent = 'City : ' + address.city;
  addressData.appendChild(div);
  div = document.createElement('div');
  div.textContent = 'State : ' + address.state;
  addressData.appendChild(div);
  div = document.createElement('div');
  div.textContent = 'Zip Code : ' + address.zip;
  addressData.appendChild(div);
  var edit = document.createElement('button');
  edit.textContent = 'edit';
  edit.addEventListener('click', function(e){
    showEditAddress(address);
  });
  addressData.appendChild(edit);
}

function showEditAddress(address){
  var addressData = document.getElementById('addressData');
  addressData.textContent = '';
  var form = document.createElement('form');
  form.id = 'editAddress';
  addressData.appendChild(form);

  var street = document.createElement('h3');
  street.textContent = 'Street address:';
  form.appendChild(street);

  var input = document.createElement('input');
  input.type = 'text';
  input.name = 'street';
  input.value = address.street;
  form.appendChild(input);
  form.appendChild(document.createElement('br'));

  var city = document.createElement('h3');
  city.textContent = 'City:';
  form.appendChild(city);

  var input = document.createElement('input');
  input.type = 'text';
  input.name = 'city';
  input.value = address.city;
  form.appendChild(input);
  form.appendChild(document.createElement('br'));

  var state = document.createElement('h3');
  state.textContent = 'State:';
  form.appendChild(state);

  var input = document.createElement('input');
  input.type = 'text';
  input.name = 'state';
  input.value = address.state;
  form.appendChild(input);
  form.appendChild(document.createElement('br'));

  var zip = document.createElement('h3');
  zip.textContent = 'Zip Code:';
  form.appendChild(zip);

  var input = document.createElement('input');
  input.type = 'number';
  input.name = 'zip';
  input.value = address.zip;
  form.appendChild(input);
  form.appendChild(document.createElement('br'));

  var submit = document.createElement('input');
  submit.type = 'submit';
  submit.name = 'submit';
  submit.value = 'submit';
  form.appendChild(submit);
  submit.addEventListener('click', function(e){
    e.preventDefault();
    editAddress(address);
  });

}

function editAddress(address){
  var xhr = new XMLHttpRequest();

  xhr.open('Put', 'api/addresses/' + address.id);
  xhr.setRequestHeader("content-type", "application/json");

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status < 400) {
        let addressObject = JSON.parse(xhr.responseText);
        getCourse(addressObject.id);
      } else {
        document.getElementById('addressData').textContent = 'Course Not Found';
      }
    }
  };
  var form = document.getElementById('editAddress');
  let addressObject = {
    id: address.id,
    city: form.city.value,
    street: form.street.value,
    state: form.state.value,
    zip: form.zip.value,
  };
  xhr.send(JSON.stringify(addressObject));
}

function addcourse(e) {
  e.preventDefault();

  var xhr = new XMLHttpRequest();

  xhr.open('Post', 'api/courses/');
  xhr.setRequestHeader("content-type", "application/json");

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status < 400) {
        let courseObject = JSON.parse(xhr.responseText);
        getCourse(courseObject.id);
      } else {
        document.getElementById('courseData').textContent = 'Course Not Found';
      }
    }
  };
  let courseObject = {
    name: document.makeCourse.name.value,
    description: document.makeCourse.description.value,
    length: document.makeCourse.length.value,
  };
  xhr.send(JSON.stringify(courseObject));

  document.makeCourse.reset();
}

function clearAll(){
	var addressData = document.getElementById('addressData');
	var courseList = document.getElementById('listCourses');
	var dataDiv = document.getElementById('courseData');
	dataDiv.textContent = '';
	courseList.textContent = '';
	addressData.textContent = '';
}
