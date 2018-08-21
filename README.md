AndroidDynamicSpinner
=====================

[![](https://jitpack.io/v/wtw-software/AndroidDynamicSpinner.svg)](https://jitpack.io/#wtw-software/AndroidDynamicSpinner)

## Usage

1. Subclass DynamicSpinner and set the item generic type.  
```MyItemSpinner extends DynamicSpinner<MyItemType>```
1. Subclass DynamicSpinnerAdapter, set generic type (same as above), and override the methods required.  
```MyItemSpinnerAdapter extends DynamicSpinnerAdapter<MyItemType> ```  
The adapter provides custom selected and dropdown views for your items. 
1. Add items to the adapter (standard procedure), and ddd the adapter to the spinner.
1. Set/implement the dynamic spinner listener. This handles clicks on items, delete button and add button.  
```myItemSpinner.setDynamicSpinnerListener(myListener) ```
1. Implement callback required by listener. (onItemDelete, onItemSelected, onItemAdd). 

## Screenshots

![](./screenshot1.png) ![](./screenshot2.png)

## Theming

The delete icon in the spinner may be replaced by an icon of your choice. First add a style to your theme:

```
...
<item name="ds_dynamicSpinnerStyle">@style/mySpinnerStyle</item>
...
```

Then add an icon reference in the style you created:

```
<style name="myDynamicSpinnerStyle">
    <item name="ds_deleteItemIcon">@drawable/my_delete_icon</item>
</style>
```


License
-------

    Copyright 2014 WTW AS

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
