# TPA Plugin

## Overview  
The **TPA Plugin** is a Minecraft Bukkit plugin that allows players to request teleportation to each other with commands. It includes features for accepting or denying teleportation requests and provides teleport request management for a better multiplayer experience.

---

## Features  
- **Teleport Request**: Players can send teleportation requests to other players.  
- **Accept/Deny Requests**: Players can accept or deny incoming teleport requests.  
- **Administrator Location Check**: Admins can check the location of any online player.  
- **Easy-to-Use Commands**: Simple and intuitive command system.  

---

## Commands  

| Command               | Description                                             | Permission    |
|-----------------------|---------------------------------------------------------|---------------|
| `/tpa <player>`       | Sends a teleport request to a player.                   | All players   |
| `/tpaccept <player>`  | Accepts a teleport request from a player.               | All players   |
| `/tpadeny <player>`   | Denies a teleport request from a player.                | All players   |
| `/tpal <player>`      | Displays the coordinates of a specified player.         | Admins only   |

---

## Installation  

1. **Download the plugin**:  
   Place the compiled `.jar` file in your server's `plugins` folder.  

2. **Restart the server**:  
   Restart your server to load the plugin.  

3. **Verify installation**:  
   Use the `/plugins` command in the server console or in-game to check if the plugin is loaded.  

---

## Usage  

### Sending a Teleport Request  
1. Use `/tpa <player>` to send a teleport request.  
2. The target player will receive a message with options to accept or deny the request.  

### Accepting or Denying a Request  
- **Accept**: Use `/tpaccept <player>` to teleport the requester to your location.  
- **Deny**: Use `/tpadeny <player>` to reject the teleport request.  

### Admin Location Check  
Admins can use `/tpal <player>` to get the coordinates of any online player.  

---

## Permissions  

| Permission Node      | Description                       |
|----------------------|-----------------------------------|
| `tpa.use`            | Grants access to `/tpa`, `/tpaccept`, and `/tpadeny`. |
| `tpa.admin`          | Grants access to `/tpal`.        |

---

## Troubleshooting  

- **Teleportation Requests Not Working**:  
  Ensure both players are online and that the plugin is properly installed.  

- **Admin Command Not Working**:  
  Verify that the user executing `/tpal` has the required permissions (`tpa.admin`).  

---

## Version Information  

- **Current Version**: 1.0.0  
- **Release Notes**:  
  - Initial release.  
  - Added basic TPA functionality and admin location checking.  

---

## License  
This plugin is distributed under the MIT License. Feel free to modify and distribute with proper attribution to the original author.  

---

## Support  

For questions, issues, or suggestions, feel free to contact:  
**Email**: gbin8498@gmail.com
