AndroidDynamicSpinner
=====================


![](https://github.com/wtw-software/AndroidDynamicSpinner/blob/master/screenshot1.png =250px)

![](https://github.com/wtw-software/AndroidDynamicSpinner/blob/master/screenshot2.png =250px)

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

