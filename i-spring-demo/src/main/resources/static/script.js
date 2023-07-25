function callRestAPI() {

  alert("Hello js");

}

const userAction = async () => {
  const response = await fetch('http://localhost:8083/studentjson');
  const myJson = await response.json(); //extract JSON from the http response
  // do something with myJson
  alert(JSON.stringify(myJson));
}