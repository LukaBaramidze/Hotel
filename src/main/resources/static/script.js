async function fetchAndDisplayHotels() {
  const response = await fetch('http://localhost:8080/hotel');

  if (!response.ok) {
    console.error(`HTTP error! Status: ${response.status}`);
    return;
  }

  const data = await response.json();
  const hotelList = data.hotelList || [];
  const hotelListElement = document.getElementById('hotelList');

  hotelListElement.innerHTML = '';

  hotelList.forEach(hotel => {
    hotelListElement.innerHTML += `
      <li class="product-item">
        <span>${hotel.name}</span>
        <button class="check-button" onclick="fetchAndDisplayRooms('${hotel.id}', '${hotel.name}')">Check Rooms</button>
      </li>
    `;
  });
}
async function fetchAndDisplayRooms(hotelId, hotelName) {
  const response = await fetch(`http://localhost:8080/room/${hotelId}`);

  if (!response.ok) {
    console.error(`HTTP error! Status: ${response.status}`);
    return;
  }

  const data = await response.json();
  const roomList = data.roomList || [];
  const modalHotelName = document.getElementById('modalHotelName');
  const modalRoomList = document.getElementById('modalRoomList');

  modalHotelName.textContent = hotelName;

  let selectHTML = '<label for="room-select">Choose a room:</label><select name="rooms" id="room-select"><option value="">--Please choose a room--</option>';

  roomList.forEach(room => {
    selectHTML += `
      <option value="${room.id}">Room ${room.roomNumber}</option>
    `;
  });

  selectHTML += '</select>';
  selectHTML += `<button onclick="bookRoom(document.getElementById('room-select').value)">Book Room</button>`; // Add the "Book Room" button
  modalRoomList.innerHTML = selectHTML;

  const modal = document.getElementById('roomModal');
  modal.style.display = 'block';

  const closeButton = document.getElementsByClassName('close')[0];
  closeButton.onclick = function() {
    modal.style.display = 'none';
  }
}
async function closeModal() {
  const modal = document.getElementById('roomModal');
  modal.style.display = 'none';
}
async function bookRoom(roomId) {
  const response = await fetch(`http://localhost:8080/room/${roomId}`, {
    method: 'PUT'
  });

  if (!response.ok) {
    console.error(`HTTP error! Status: ${response.status}`);
    return;
  }

  const data = await response.text();
  if (data === "Room successfully booked for one day!") {
    alert('Room successfully booked for one day!');
  } else {
    alert('Room couldn\'t be booked: Room not available!');
  }
  const modal = document.getElementById('roomModal');
  modal.style.display = 'none';

}

window.onload = fetchAndDisplayHotels;