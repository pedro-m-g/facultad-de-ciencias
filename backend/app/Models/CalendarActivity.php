<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CalendarActivity extends Model
{
    use HasFactory;

    protected $fillable = [
        'title',
        'start_date',
        'start_time',
        'end_date',
        'end_time',
        'is_all_day'
    ];

    protected function casts(): array
    {
        return [
            'start_date' => 'date',
            'start_time' => 'datetime',
            'end_date' => 'date',
            'end_time' => 'datetime',
            'is_all_day' => 'boolean'
        ];
    }

}
