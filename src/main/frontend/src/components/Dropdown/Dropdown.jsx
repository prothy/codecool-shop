import React from 'react'
import './Dropdown.css'

function Dropdown() {
  return (
    <select name="sort" id="sort">
      <option value="sort-by" disabled selected>
        Sort by
      </option>
    </select>
  )
}

export default Dropdown
