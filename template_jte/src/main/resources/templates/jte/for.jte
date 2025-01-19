@import com.jiabin.template.jte.practice.test.Page
@import gg.jte.support.ForSupport
@param Page model

@for(String entry : model.entries)
  <li>${entry}</li>
@endfor
@for(var entry : model.entries)
  <li>${entry}</li>
@endfor
@for(int i = 0; i < 10; ++i)
  <li>i is ${i}</li>
@endfor
<hr/>
@for(var entryLoop : ForSupport.of(model.entries))
<tr class="${(entryLoop.getIndex() + 1) % 2 == 0 ? "even" : "odd"}">
    ${entryLoop.get()}
</tr>
@endfor
