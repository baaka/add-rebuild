// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract Election {
  struct AppForm {
    uint id;
    string name;
    uint upVoteCount;
    uint downVoteCount;
  }


uint public appFormCount;
mapping(uint => mapping(address => bool)) public voters;
mapping(uint => AppForm) public appForms;


function addAppForm (string memory _name) public {
  appFormCount ++;
  appForms[appFormCount] = AppForm(appFormCount, _name, 0, 0);
}


event votedEvent (
  uint indexed _appFormId
);

function upVote (uint _appFormId) public {
  require(!voters[_appFormId][msg.sender]);

  require(_appFormId > 0 && _appFormId <= appFormCount);

  voters[_appFormId][msg.sender] = true;

  appForms[_appFormId].upVoteCount ++;
  emit votedEvent(_appFormId);
}

function downVote (uint _appFormId) public {
  require(!voters[_appFormId][msg.sender]);

  require(_appFormId > 0 && _appFormId <= appFormCount);

  voters[_appFormId][msg.sender] = true;

  appForms[_appFormId].downVoteCount ++;
  emit votedEvent(_appFormId);
}

function getUpVotes(uint _appFormId) public  returns(uint count){
  return appForms[_appFormId].upVoteCount;
}

function getDownVotes(uint _appFormId) public returns(uint count){
  return appForms[_appFormId].downVoteCount;
}

}
